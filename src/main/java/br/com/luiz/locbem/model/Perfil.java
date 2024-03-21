package br.com.luiz.locbem.model;

public enum Perfil {
    USER("USER"),
    ADMIN("ADMIN");

    private String authority;

    Perfil(String admin) {
    }

    public String getAuthority() {
        return authority;
    }
}

