package com.financeiro.api.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.financeiro.api.dtos.UsuarioDTO;
import com.financeiro.api.security.JwtUser;

public final class UserUtil {

	private UserUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static Long getCodeUser() {
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
	
	public static String getNameUser() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getNome() + user.getSobrenome();
    }

	public static String getEmail() {
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
	
	public static UsuarioDTO getUsuarioDto() {
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserDto();
    }
}
