package br.com.luiz.locbem.model.user;

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

