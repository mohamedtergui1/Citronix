package ma.tr.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.tree.TreeRequest;
import ma.tr.citronix.dto.tree.TreeResponse;
import ma.tr.citronix.entity.Tree;
import ma.tr.citronix.mapper.TreeMapper;
import ma.tr.citronix.service.tree.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/trees")
@RestController
public class TreeController  {

    private final TreeService treeService;


    @GetMapping
    public ResponseEntity<List<TreeResponse>> getTrees() {
        return new ResponseEntity<>(treeService.getAllTrees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponse> getTree(@PathVariable Long id) {
        return new ResponseEntity<>(treeService.getTreeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TreeResponse> createTree(@RequestBody @Valid TreeRequest treeRequest) {
        return new ResponseEntity<>(treeService.createTree(treeRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeResponse> updateTree(@PathVariable Long id, @RequestBody @Valid TreeRequest treeRequest) {
        return new ResponseEntity<>(treeService.updateTree(id, treeRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTree(@PathVariable Long id) {
        treeService.deleteTreeById(id);
        return new ResponseEntity<>("Tree deleted", HttpStatus.OK);
    }

}
