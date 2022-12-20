package ams.cs.ms.siemens.com.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Port")
public class Port {

    @Id @GeneratedValue
    private Long id;
    private Integer portNo;
    private String portName;
    private String portType;


    public Port() {
    }

    public Long getId() {
        return id;
    }

    public Integer getPortNo() {
        return portNo;
    }

    public String getPortName() {
        return portName;
    }

    public String getPortType() {
        return portType;
    }
}
