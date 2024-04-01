package gr6.se2.model.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "account")
public class Account {
    /**
     * Refer to the id of student, teacher or admin
     */
    @Id
    @Column(name = "ownerId")
    private Integer ownerId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;
    /**
     * A timestamp in millisecond.
     * <p>
     * Only access tokens that have created time greater or equal than
     * this timestamp can be authorized.
     * <p>
     * Once user has logged-out, all access tokens created before that log-out action
     * must be invalidated. This field help us achieve that.
     */
    private long accessTokenValidTimestamp;

    public enum Role {
        STUDENT, TEACHER, ADMIN
    }

    /**
     * Check if an access token with the following created time
     * can be used for authorizing this account
     * <p>
     * Only access tokens created after the last log-out action of this
     * account can be used for authorization. Therefore, we can only
     * accept access tokens whose created time is greater than or equal to
     * the {@link #accessTokenValidTimestamp} value.
     *
     * @param accessTokenCreatedTime the created time of the access token
     * @return true if the access token can be used to authorize this account
     */
    public boolean acceptAccessTokenCreatedAt(Date accessTokenCreatedTime) {
        return accessTokenCreatedTime.toInstant().toEpochMilli() > accessTokenValidTimestamp;
    }
}
