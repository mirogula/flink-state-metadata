/*
 * Copyright 2020 Michal Klempa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.michalklempa.flink.state.metadata;

import org.apache.flink.runtime.checkpoint.savepoint.SavepointV2;

public interface Move {
    default SavepointV2 move(SavepointV2 savepoint, String sourceUri, String destinationUri) throws Exception {
        return new MoveImpl(savepoint, sourceUri, destinationUri).convert();
    }

    class Default implements Move {
    }

    abstract class AbstractMove {
        protected final SavepointV2 savepoint;
        protected final String sourceUri;
        protected final String destinationUri;

        public AbstractMove(final SavepointV2 savepoint, final String sourceUri, final String destinationUri) {
            this.savepoint = savepoint;
            this.sourceUri = sourceUri;
            this.destinationUri = destinationUri;
        }

        public abstract SavepointV2 convert() throws Exception;
    }
}
