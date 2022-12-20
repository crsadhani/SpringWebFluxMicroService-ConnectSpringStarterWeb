package ams.cs.ms.siemens.com.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.stereotype.Component;

import java.util.*;

@Node("Device")
@Component
public class Device {

    @Id @GeneratedValue
    private Long id;

    private String hostname;

    private String name;
    private String ip;
    private String type;
    @Relationship(type = "hasPort", direction = Relationship.Direction.OUTGOING)
    private List<Port> ports = new ArrayList<>();

    @Relationship(type = "CONTAINS", direction = Relationship.Direction.INCOMING)
    private List<PRP_Group> groups = new ArrayList<>();

    public Device() {
    }

    public List<Port> getPorts() {
        return ports;
    }

    public Long getId() {
        return id;
    }

    public String getHostname() {
        return hostname;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getType() {
        return type;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public Device(List<PRP_Group> groups) {
        this.groups = groups;
    }
}
