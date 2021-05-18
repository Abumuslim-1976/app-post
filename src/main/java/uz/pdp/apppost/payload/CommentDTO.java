package uz.pdp.apppost.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.apppost.entity.Post;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    @NotNull(message = "textni kiriting")
    private String text;

    @NotNull(message = "commentni qaysi postga tegishli ekanligini kiriting")
    private Long postId;
}
