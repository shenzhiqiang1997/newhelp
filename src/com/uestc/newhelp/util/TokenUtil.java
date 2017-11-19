package com.uestc.newhelp.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class TokenUtil {
	private static Key key=MacProvider.generateKey(SignatureAlgorithm.HS512);
	public static String getToken(String username) {
		String token=Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setIssuer("Shen")
			.signWith(SignatureAlgorithm.HS512, key)
			.compact();
		return token;
	}
	public static Claims parseToken(String token) {
		Jws<Claims> jwt= Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		return jwt.getBody();
	}
}
