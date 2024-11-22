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
public class TreeController implements BaseController {

    private final TreeService treeService;
    private final TreeMapper treeMapper;

    @GetMapping
    public ResponseEntity<List<TreeResponse>> getTrees() {
        return responseEntity(treeService.getAllTrees().stream().map(this::toTreeResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponse> getTree(@PathVariable Long id) {
        return responseEntity(toTreeResponse(treeService.getTreeById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TreeResponse> createTree(@RequestBody @Valid TreeRequest treeRequest) {
        return responseEntity(toTreeResponse(treeService.createTree(toTree(treeRequest))), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeResponse> updateTree(@PathVariable Long id,@RequestBody @Valid TreeRequest treeRequest) {
        return responseEntity(toTreeResponse(treeService.updateTree(id, toTree(treeRequest))), HttpStatus.OK);
    }

    private TreeResponse toTreeResponse(Tree tree) {
        return treeMapper.toTreeRequest(tree);
    }

    private Tree toTree(TreeRequest treeRequest) {
        return treeMapper.toTree(treeRequest);
    }

}
