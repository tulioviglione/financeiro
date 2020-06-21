package com.financeiro.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.financeiro.api.dtos.UsuarioDTO;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -268046329085485932L;

	private Long id;
	private String password;
	private String nome;
	private String sobrenome;
	private UsuarioDTO userDto;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUser(Long id, String nome, String sobrenome, String email, String password, Collection<? extends GrantedAuthority> authorities, UsuarioDTO userDto) {
		this.id = id;
		this.password = password;
		this.authorities = authorities;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.userDto = userDto;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public UsuarioDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UsuarioDTO userDto) {
		this.userDto = userDto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
