package ma.tr.citronix.service.tree;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.tree.TreeRequest;
import ma.tr.citronix.entity.Field;
import ma.tr.citronix.entity.Tree;
import ma.tr.citronix.exception.NotCompleteProcess;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.repository.FieldRepository;
import ma.tr.citronix.repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TreeServiceImpl implements TreeService {
    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;

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
        if (!treeRepository.existsById(id)) {
            throw new NotCompleteProcess("Tree not found");
        }
        treeRepository.deleteById(id);

    }

    @Override
    public Tree createTree(Tree tree) {
        Field field = fieldRepository.findById(tree.getField().getId()).orElseThrow(() -> new NotCompleteProcess("Field not found"));
        if (field.getArea() / (treeRepository.getCountByFieldId(field.getId()) + 1) < 100) {
            throw new NotCompleteProcess("Field don't have enough space");
        }

        return treeRepository.save(tree);
    }

    @Override
    public Tree updateTree(Long id, TreeRequest treeRequest) {
        if (!treeRepository.existsById(id)) {
            throw new NotCompleteProcess("Tree not found");
        }
        return null;
    }
}
