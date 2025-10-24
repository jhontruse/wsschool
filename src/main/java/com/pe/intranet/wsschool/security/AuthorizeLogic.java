package com.pe.intranet.wsschool.security;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//Clase S8
@Component
public class AuthorizeLogic {

    // Reglas de autorización por “clave de método/acción”.
    // Usa nombres cortos y consistentes; evita hardcodear en switches.
    private static final Map<String, Set<String>> REQUIRED_ROLES = Map.ofEntries(
            // Usuarios: administración completa
            Map.entry("findMenuByUsername", roles("ALUMNO")),
            Map.entry("findPersonaByUsername", roles("ALUMNO")),
            Map.entry("findById", roles("USER", "DBA")),
            Map.entry("getBydId", roles("USER", "DBA"))

    // Si no se encuentra la clave, abajo hay un default (ROOT)
    );

    public boolean hasAccess(String key) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return hasAccess(key, auth);
    }

    /**
     * Sobrecarga testeable y reutilizable.
     */
    public boolean hasAccess(String key, Authentication auth) {
        if (!isAuthenticated(auth))
            return false;

        // Roles requeridos para la acción; default: ROOT
        Set<String> required = REQUIRED_ROLES.getOrDefault(key, roles("ROOT"));

        // Normaliza y junta los roles del usuario
        Set<String> userRoles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(Objects::nonNull)
                .map(AuthorizeLogic::normalizeRole)
                .collect(Collectors.toUnmodifiableSet());

        // ¿Hay intersección entre los roles del usuario y los requeridos?
        return !java.util.Collections.disjoint(userRoles, required);
    }

    // ---------- Helpers ----------

    private static Set<String> roles(String... roles) {
        return java.util.Arrays.stream(roles)
                .map(AuthorizeLogic::normalizeRole)
                .collect(Collectors.toUnmodifiableSet());
    }

    private static String normalizeRole(String role) {
        String r = role.trim().toUpperCase(Locale.ROOT);
        return r.startsWith("ROLE_") ? r : "ROLE_" + r;
    }

    private static boolean isAuthenticated(Authentication auth) {
        return auth != null
                && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken);
    }

}
