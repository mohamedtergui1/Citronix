package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.tree.TreeRequest;
import ma.tr.citronix.dto.tree.TreeResponse;
import ma.tr.citronix.entity.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    @Mapping(source = "fieldId" , target = "field.id")
    Tree toTree(TreeRequest treeRequest);

    TreeResponse toTreeRequest(Tree tree);
}