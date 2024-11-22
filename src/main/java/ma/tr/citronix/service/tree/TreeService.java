package ma.tr.citronix.service.tree;

import ma.tr.citronix.dto.tree.TreeRequest;
import ma.tr.citronix.entity.Tree;

import java.util.List;

public interface TreeService {
    List<Tree> getAllTrees();

    Tree getTreeById(Long id);

    void deleteTreeById(Long id);

    Tree createTree(Tree tree);

    Tree updateTree(Long id , Tree tree);
}
