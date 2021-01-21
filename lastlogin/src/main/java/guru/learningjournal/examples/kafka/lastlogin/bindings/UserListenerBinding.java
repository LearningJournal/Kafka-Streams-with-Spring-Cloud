package guru.learningjournal.examples.kafka.lastlogin.bindings;

import guru.learningjournal.examples.kafka.lastlogin.model.UserDetails;
import guru.learningjournal.examples.kafka.lastlogin.model.UserLogin;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;

public interface UserListenerBinding {

    @Input("user-master-channel")
    KTable<String, UserDetails> userInputStream();

    @Input("user-login-channel")
    KTable<String, UserLogin> loginInputStream();

}
