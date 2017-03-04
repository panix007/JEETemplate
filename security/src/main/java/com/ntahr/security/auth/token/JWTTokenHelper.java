package com.ntahr.security.auth.token;

import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.ntahr.common.properties.PropertiesConstants;
import com.ntahr.common.properties.PropertiesUtil;
import com.ntahr.security.auth.Token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTokenHelper {

	// The signing key would be read from application configuration.
	static Key key = generateKey();

	static private Key generateKey() {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee,
					(byte) 0x99 };
			KeySpec keySpec = new PBEKeySpec(
					PropertiesUtil.getInstance().getProperty(PropertiesConstants.KEY_GENERATOR_VALUE).toCharArray(),
					salt, 65536, 256);
			SecretKey secretKey = factory.generateSecret(keySpec);
			SecretKey secretKeyEncoded = new SecretKeySpec(secretKey.getEncoded(), "AES");
			return secretKeyEncoded;
		} catch (Exception e) {
			return null;
		}

	}

	static public Token generateToken(String subject) {

		JwtBuilder jwtBuilder = Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key);
		Token token = new Token();
		String expirationTime = PropertiesUtil.getInstance().getProperty(PropertiesConstants.TOKEN_EXPIRATION_MILLIS);
		if (expirationTime != null && !expirationTime.isEmpty()) {
			Long tokenExpiration = System.currentTimeMillis() + Long.parseLong(expirationTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(tokenExpiration);
			Date expiryDate = calendar.getTime();
			jwtBuilder.setExpiration(expiryDate); // Set Expiration
			token.setExpiration(expiryDate.toString());
		}

		String tokenString = jwtBuilder.compact();
		token.setToken(tokenString);
		token.setApiKey("");
		return token;
	}

	static public boolean validateToken(Token token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token.getToken());
			// OK, we can trust this JWT
			return true;

		} catch (Exception e) {
			// don't trust the JWT!
			return false;
		}
	}
}
