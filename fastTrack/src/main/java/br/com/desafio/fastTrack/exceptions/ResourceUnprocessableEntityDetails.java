package br.com.desafio.fastTrack.exceptions;

import javax.annotation.Generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceUnprocessableEntityDetails {

	private String title;
	private int status;
	private String detail;
	private long timestamp;
	private String developerMessage;

	@Generated("SparkTools")
	private ResourceUnprocessableEntityDetails(Builder builder) {
		this.title = builder.title;
		this.status = builder.status;
		this.detail = builder.detail;
		this.timestamp = builder.timestamp;
		this.developerMessage = builder.developerMessage;
	}

	/**
	 * Creates builder to build {@link ResourceUnprocessableEntityDetails}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static ITitleStage builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public interface ITitleStage {
		public IStatusStage withTitle(String title);
	}

	@Generated("SparkTools")
	public interface IStatusStage {
		public IDetailStage withStatus(int status);
	}

	@Generated("SparkTools")
	public interface IDetailStage {
		public ITimestampStage withDetail(String detail);
	}

	@Generated("SparkTools")
	public interface ITimestampStage {
		public IDeveloperMessageStage withTimestamp(long timestamp);
	}

	@Generated("SparkTools")
	public interface IDeveloperMessageStage {
		public IBuildStage withDeveloperMessage(String developerMessage);
	}

	@Generated("SparkTools")
	public interface IBuildStage {
		public ResourceUnprocessableEntityDetails build();
	}

	/**
	 * Builder to build {@link ResourceUnprocessableEntityDetails}.
	 */
	@Generated("SparkTools")
	public static final class Builder
			implements ITitleStage, IStatusStage, IDetailStage, ITimestampStage, IDeveloperMessageStage, IBuildStage {
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;

		private Builder() {
		}

		@Override
		public IStatusStage withTitle(String title) {
			this.title = title;
			return this;
		}

		@Override
		public IDetailStage withStatus(int status) {
			this.status = status;
			return this;
		}

		@Override
		public ITimestampStage withDetail(String detail) {
			this.detail = detail;
			return this;
		}

		@Override
		public IDeveloperMessageStage withTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		@Override
		public IBuildStage withDeveloperMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		@Override
		public ResourceUnprocessableEntityDetails build() {
			return new ResourceUnprocessableEntityDetails(this);
		}
	}
}
