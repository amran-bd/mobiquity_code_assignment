import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author AmranHossain on 19/9/2564
 */
public class Main {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new APIException("File path is required!");
            }
            final String pack = Packer.pack(args[0]);
            System.out.println(String.format("Output:\r\n%s", pack));
        } catch (Exception e) {
            System.out.println(Optional.of(e.getMessage()).orElse("Gotten error while executing..."));
        }
    }
}
