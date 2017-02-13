package org.elasticsearch.index.analysis.concatenate;

import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;

import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class ConcatenateTokenFilterFactory extends AbstractTokenFilterFactory
{
	private String tokenSeparator = null;
	private int incrementGap = 100;

	@Inject 
	public ConcatenateTokenFilterFactory(IndexSettings indexSettings, Environment env, @Assisted String name, @Assisted Settings settings)
	{
		super(indexSettings, name, settings);
		// The token_separator is defined in the ES configuration file
		tokenSeparator = settings.get("token_separator");
		incrementGap = settings.getAsInt("increment_gap", 100);
	}

	@Override 
	public TokenStream create(TokenStream tokenStream)
	{
		return new ConcatenateFilter(tokenStream, tokenSeparator, incrementGap);
	}
}
