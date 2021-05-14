package rev.team.PROBLEM_SERVICE.domain.dto;

import java.util.List;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rev.team.PROBLEM_SERVICE.domain.entity.Submit;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class SubmitDTO {

    private String userId;

    private Set<Submit> submitList;
}
