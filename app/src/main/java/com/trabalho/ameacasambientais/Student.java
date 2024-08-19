package com.trabalho.ameacasambientais;

import androidx.annotation.NonNull;

public class Student {
    private String endereco;

    private String desc;

    private Long id;
    private String data;

    public Student( String endereco, String data) {
        this.endereco = endereco;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    @NonNull
    @Override
    public String toString(){
      return endereco + "\n" + data;
    }
}
