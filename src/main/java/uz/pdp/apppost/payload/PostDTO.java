package uz.pdp.apppost.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    @NotNull(message = "sarlavha kiritilsin")
    private String title;

    @NotNull(message = "text kiritilsin")
    private String text;

    @NotNull(message = "URL kiritilsin")
    private String url;

}
