package com.ssm.batch.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;


/**
 * @Package: com.ssm.batch.listener
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/27 20:41
 */
@Component
public class CommonChunkListener implements ChunkListener {

	@Override
	public void beforeChunk(ChunkContext chunkContext) {
		System.out.println("before chunk--------");
	}

	@Override
	public void afterChunk(ChunkContext chunkContext) {
		System.out.println("after chunk---------");
	}

	@Override
	public void afterChunkError(ChunkContext chunkContext) {

	}
}
