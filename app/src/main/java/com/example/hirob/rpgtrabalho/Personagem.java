package com.example.hirob.rpgtrabalho;

/**
 * Created by hirob on 02/11/2017.
 */

public class Personagem {
    private String nmPersonagem;
    private int atkMax;
    private int  atkMin;
    private int defense;
    private int gold;
    private boolean jogavel;
    private String itemUm;
    private String itemDois;
    private int hpTotal;
    private int hpAtual;

    public String getNmPersonagem() {
        return nmPersonagem;
    }

    public void setNmPersonagem(String nmPersonagem) {
        this.nmPersonagem = nmPersonagem;
    }

    public int getAtkMax() {
        return atkMax;
    }

    public void setAtkMax(int atkMax) {
        this.atkMax = atkMax;
    }

    public int getAtkMin() {
        return atkMin;
    }

    public void setAtkMin(int atkMin) {
        this.atkMin = atkMin;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isJogavel() {
        return jogavel;
    }

    public void setJogavel(boolean jogavel) {
        this.jogavel = jogavel;
    }

    public String getItemUm() {
        return itemUm;
    }

    public void setItemUm(String itemUm) {
        this.itemUm = itemUm;
    }

    public String getItemDois() {
        return itemDois;
    }

    public void setItemDois(String itemDois) {
        this.itemDois = itemDois;
    }

    public int intJogavel(){
        if(jogavel){
            return 1;
        }else{
            return 0;
        }
    }

    public int getHpTotal() {
        return hpTotal;
    }

    public void setHpTotal(int hpTotal) {
        this.hpTotal = hpTotal;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public void setHpAtual(int hpAtual) {
        this.hpAtual = hpAtual;
    }
}
