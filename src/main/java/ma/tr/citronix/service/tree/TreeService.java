package ma.tr.citronix.service.tree;

import ma.tr.citronix.dto.tree.TreeRequest;
import ma.tr.citronix.dto.tree.TreeResponse;
import ma.tr.citronix.entity.Tree;

import java.util.List;

public interface TreeService {
    List<TreeResponse> getAllTrees();

    TreeResponse getTreeById(Long id);

    void deleteTreeById(Long id);

    TreeResponse createTree(TreeRequest treeRequest);

    TreeResponse updateTree(Long id , TreeRequest treeRequest);
}
