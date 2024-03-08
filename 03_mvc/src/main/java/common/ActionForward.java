package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data    //getter setter toStroing equals  다 들어있는 친구임. 이제 이거 쓸게용. 근데 builder는 안들어있으니까 넣어줘야함!
public class ActionForward {
  private String view;
  private boolean isRedirect;
}
