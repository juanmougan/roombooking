package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable final long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundException(Room.class.getSimpleName(), id));
    }

    @PostMapping
    public Room createRoom(@RequestBody final Room request) {
        Room room = Room.from(request);
        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    public Room editRoom(@PathVariable final long id, @RequestBody final Room request) {
        Room original = roomRepository.getOne(id);
        original.copyAttributesFrom(request);
        return roomRepository.save(original);
    }
}
