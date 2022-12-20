package ams.cs.ms.siemens.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PRP_Group {
    @Id
    @GeneratedValue
    private Long id;
    private String groupType;

    private String name;

    @Relationship(type="CONTAINS", direction = Relationship.Direction.OUTGOING)
    private List<Device> devices = new ArrayList<>();

    public List<Device> getDevices() {
        return devices;
    }

    public Long getId() {
        return id;
    }

    public String getGroupType() {
        return groupType;
    }

    public String getName() {
        return name;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
