package ma.tr.citronix.service.tree;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.tree.TreeRequest;
import ma.tr.citronix.entity.Tree;
import ma.tr.citronix.exception.NotCompleteProcess;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TreeServiceImpl implements TreeService {
    private final TreeRepository treeRepository;


    @Override
    public List<Tree> getAllTrees() {
        return treeRepository.findAll();
    }

    @Override
    public Tree getTreeById(Long id) {
        return treeRepository.findById(id).orElseThrow(() -> new NotFoundException("Tree not found"));
    }

    @Override
    public void deleteTreeById(Long id) {
        if (treeRepository.existsById(id)) {
            treeRepository.deleteById(id);
        }
        throw new NotCompleteProcess("Tree not found");
    }

    @Override
    public Tree createTree(Tree tree) {
        return null;
    }

    @Override
    public Tree updateTree(Long id, TreeRequest treeRequest) {
        return null;
    }
}
