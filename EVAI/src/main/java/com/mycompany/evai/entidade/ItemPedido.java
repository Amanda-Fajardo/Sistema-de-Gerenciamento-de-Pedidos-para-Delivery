package com.mycompany.evai.entidade;

public class ItemPedido {
    private int id;
    private int idProduto;
    private int idPedido;
    private int quantidade;
    private float valorUnitario;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the produto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @param produto the produto to set
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valorUnitario
     */
    public float getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
