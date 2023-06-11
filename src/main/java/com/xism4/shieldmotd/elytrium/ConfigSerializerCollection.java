package com.xism4.shieldmotd.elytrium;

import java.util.Collection;

/**
 * Original https://github.com/Elytrium/java-commons
 */
public interface ConfigSerializerCollection {

    Collection<ConfigSerializer<?, ?>> serializers();

}