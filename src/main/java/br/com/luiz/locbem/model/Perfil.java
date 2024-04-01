package br.com.luiz.locbem.model;

public enum Perfil {
    USER("USER"),
    ADMIN("ADMIN");

    private String authority;

    Perfil(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}

