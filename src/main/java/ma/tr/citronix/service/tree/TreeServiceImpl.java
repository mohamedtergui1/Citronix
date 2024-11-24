package ma.tr.citronix.service.tree;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.tree.TreeRequest;
import ma.tr.citronix.dto.tree.TreeResponse;
import ma.tr.citronix.entity.Field;
import ma.tr.citronix.entity.Tree;
import ma.tr.citronix.exception.ProcessNotCompleted;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.mapper.TreeMapper;
import ma.tr.citronix.repository.FieldRepository;
import ma.tr.citronix.repository.TreeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;
    private final TreeMapper treeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TreeResponse> getAllTrees() {
        return treeRepository.findAll().stream().map(treeMapper::toTreeResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)

    public TreeResponse getTreeById(Long id) {
        return treeMapper.toTreeResponse(treeRepository.findById(id).orElseThrow(() -> new NotFoundException("Tree not found")));
    }

    @Override
    @Transactional
    public void deleteTreeById(Long id) {
        if (!treeRepository.existsById(id)) {
            throw new ProcessNotCompleted("Tree not found");
        }
        treeRepository.deleteById(id);

    }

    @Override
    @Transactional
    public TreeResponse createTree(TreeRequest treeRequest) {
        Tree tree = treeMapper.toTree(treeRequest);
        Field field = fieldRepository.findById(tree.getField().getId()).orElseThrow(() -> new ProcessNotCompleted("Field not found"));
        if (field.getArea() / (treeRepository.getCountByFieldId(field.getId()) + 1) < 100) {
            throw new ProcessNotCompleted("Field don't have enough space");
        }
        tree.setField(field);
        return treeMapper.toTreeResponse(treeRepository.save(tree));
    }

    @Override
    @Transactional
    public TreeResponse updateTree(Long id, TreeRequest treeRequest) {
        Tree tree = treeMapper.toTree(treeRequest);
        if (!treeRepository.existsById(id)) {
            throw new ProcessNotCompleted("Tree not found");
        }
        if (!fieldRepository.existsById(tree.getField().getId())) {
            throw new ProcessNotCompleted("field not found");
        }
        tree.setId(id);

        return treeMapper.toTreeResponse(treeRepository.save(tree));
    }

}
