package br.com.rafaelsilva91.helpdesk.domain.enums;

public enum StatusEnum {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    StatusEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusEnum toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }

        for(StatusEnum perfil : StatusEnum.values()){
            if(codigo.equals(perfil.getCodigo())){
                return perfil;
            }
        }
        throw new IllegalArgumentException("Status Inválido");
    }
    
}
