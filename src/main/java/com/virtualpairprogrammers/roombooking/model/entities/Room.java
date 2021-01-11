package com.virtualpairprogrammers.roombooking.model.entities;

import com.virtualpairprogrammers.roombooking.model.Layout;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "location cannot be blank")
    private String location;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<LayoutCapacity> capacities;

    public Room(String name, String location) {
        this.name = name;
        this.location = location;
        capacities = new ArrayList<>();
        for (Layout layout : Layout.values()) {
            capacities.add(new LayoutCapacity(layout, 0));
        }
    }

    public Room() {
    }

    public static Room from(Room original) {
        return Room.builder()
                .name(original.getName())
                .location(original.getLocation())
                .capacities(original.getCapacities())
                .build();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<LayoutCapacity> getCapacities() {
        return capacities;
    }

    public void setCapacities(List<LayoutCapacity> capacities) {
        this.capacities = capacities;
    }

    public void setCapacity(LayoutCapacity capacity) {
        for (LayoutCapacity lc : capacities) {
            if (lc.getLayout() == capacity.getLayout()) {
                lc.setCapacity(capacity.getCapacity());
            }
        }
    }

    public void copyAttributesFrom(final Room other) {
        this.name = other.name;
        this.location = other.location;
        this.capacities = other.capacities;
    }
}
