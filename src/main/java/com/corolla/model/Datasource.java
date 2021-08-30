package com.corolla.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XStreamAlias("datasource")
@NoArgsConstructor
public class Datasource implements Comparable<Datasource> {

    @XStreamAsAttribute
    private int index;
    private String name;
    private String type;
    private String url;
    private String username;
    private String password;

    public Datasource(String name, String type, String url, String username, String password) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public int compareTo(Datasource entity) {
        return this.index - entity.getIndex();
    }

}
