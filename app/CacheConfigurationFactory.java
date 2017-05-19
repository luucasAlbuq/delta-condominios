import net.sf.ehcache.config.CacheConfiguration;

/**
 * Gerador de configurações de cache
 */
public class CacheConfigurationFactory {

	private static final String CACHE_STRATEGY = "LRU";
	private static final long THREAD_INTERVAL = 120L;
	private static final int ELEMENTS_ON_DISK = 10000000;
	private static final long TIME_TO_EXPIRES = 120L;
	private static final int MAX_ENTRIES = 256000000;

	/**
	 * Retorna a configuração de um cache LRU
	 */
	public static CacheConfiguration getCacheConfigurationLRU() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration(
				"delta-cache", MAX_ENTRIES);
		cacheConfiguration.setEternal(false);
		cacheConfiguration.setTimeToIdleSeconds(TIME_TO_EXPIRES);
		cacheConfiguration.setTimeToLiveSeconds(TIME_TO_EXPIRES);
		cacheConfiguration.setMaxElementsOnDisk(ELEMENTS_ON_DISK);
		cacheConfiguration.setDiskExpiryThreadIntervalSeconds(THREAD_INTERVAL);
		cacheConfiguration.setMemoryStoreEvictionPolicy(CACHE_STRATEGY);
		return cacheConfiguration;
	}
}
