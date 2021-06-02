package me.pedrocaires.fff.user;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.user.model.User;
import me.pedrocaires.fff.user.model.UserToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

	private final Key key;

	public JwtService(@Value("${jwt.key}") String jwtKey) {
		this.key = new SecretKeySpec((jwtKey).getBytes(StandardCharsets.UTF_8), "HmacSHA256");
	}

	public String issueToken(User user) {
		long currentTimeInMs = System.currentTimeMillis();
		long tenMinutesInMs = 600000;
		return Jwts.builder().setIssuedAt(new Date(currentTimeInMs))
				.setExpiration(new Date(currentTimeInMs + tenMinutesInMs)).claim("userId", user.getId())
				.claim("userName", user.getName()).claim("accountId", user.getAccountId()).signWith(key).compact();
	}

	public UserToken validateToken(String token) {
		try {
			var claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
			var userToken = new UserToken();
			userToken.setId(claims.get("userId", Integer.class));
			userToken.setName(claims.get("userName", String.class));
			userToken.setAccountId(claims.get("accountId", Integer.class));
			userToken.setExpiresAt(claims.getExpiration());
			return userToken;
		}
		catch (JwtException | NullPointerException ex) {
			throw new UnauthorizedException();
		}
	}

}
