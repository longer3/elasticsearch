/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.store.fs;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockFactory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.SmbDirectoryWrapper;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.settings.IndexSettings;
import org.elasticsearch.index.shard.ShardId;
import org.elasticsearch.index.store.IndexStore;

import java.io.IOException;
import java.nio.file.Path;

public class SmbMmapFsDirectoryService extends FsDirectoryService {

    @Inject
    public SmbMmapFsDirectoryService(ShardId shardId, @IndexSettings Settings indexSettings, IndexStore indexStore) {
        super(shardId, indexSettings, indexStore);
    }

    @Override
    protected Directory newFSDirectory(Path location, LockFactory lockFactory) throws IOException {
        logger.debug("wrapping MMapDirectory for SMB");
        return new SmbDirectoryWrapper(new MMapDirectory(location, buildLockFactory()));
    }
}
