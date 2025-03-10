/*
 *     Copyright 2016 - 2017 Florian Spieß
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libs.club.minnced.discord.rpc;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
typedef struct DiscordJoinRequest {
    const char* userId;
    const char* username;
    const char* avatar;
} DiscordJoinRequest;
 */
/**
 * Struct binding for a discord join request.
 */
public class DiscordJoinRequest extends Structure
{
    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList(
            "userId",
            "username",
            "discriminator",
            "avatar"
    ));

    /**
     * The userId for the user that requests to join
     */
    public String userId;

    /**
     * The username of the user that requests to join
     */
    public String username;

    /**
     * The discriminator of the user that requests to join
     */
    public String discriminator;

    /**
     * The avatar of the user that requests to join
     */
    public String avatar;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof DiscordJoinRequest))
            return false;
        DiscordJoinRequest that = (DiscordJoinRequest) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(username, that.username)
                && Objects.equals(discriminator, that.discriminator)
                && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, username, discriminator, avatar);
    }

    @Override
    protected List<String> getFieldOrder()
    {
        return FIELD_ORDER;
    }
}
