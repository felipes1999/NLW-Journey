package com.rocketseat.planner.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private  ParticipantRepository repository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> Participant = this.repository.findById(id);

        if (Participant.isPresent()){
            Participant rawPArticipant = Participant.get();
            rawPArticipant.setIsConfirmed(true);
            rawPArticipant.setName(payload.name());

            return ResponseEntity.ok(rawPArticipant);

        }
        return ResponseEntity.notFound().build();
    }

}
