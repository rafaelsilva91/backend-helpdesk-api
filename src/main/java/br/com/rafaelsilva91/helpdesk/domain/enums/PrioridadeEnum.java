package br.com.rafaelsilva91.helpdesk.domain.enums;

public enum PrioridadeEnum {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    PrioridadeEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PrioridadeEnum toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }

        for(PrioridadeEnum perfil : PrioridadeEnum.values()){
            if(codigo.equals(perfil.getCodigo())){
                return perfil;
            }
        }
        throw new IllegalArgumentException("Prioridade Inválida");
    }
    
}
