package test.projekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.projekat.model.SecurityUser;
import test.projekat.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final SecurityUser user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
		final List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
				.collect(Collectors.toList());

		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
}
