package com.example.quincy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quincy.data.API.ApiClient;
import com.example.quincy.data.API.ApiService;
import com.example.quincy.data.model.MangaModel;
import com.example.quincy.data.repository.MangaRepository;

import java.util.List;

public class MangaViewModel extends ViewModel {

    private final MangaRepository mangaRepository;

    public MangaViewModel() {
        ApiService apiService = ApiClient.getClient()
                .create(ApiService.class);

        mangaRepository = new MangaRepository(apiService);
    }

    // Latest Manga
    public LiveData<List<MangaModel>> getLatestMangaList(
            int limit, int offset) {

        return mangaRepository.getLatestMangaList(limit, offset);
    }

    // Most Popular Manga e
    public LiveData<List<MangaModel>> getPopularMangaList(
            int limit, int offset) {

        return mangaRepository.getPopularMangaList(limit, offset);
    }

    // Nomor chapter terbaru e
    public LiveData<String> getLatestChapter(String mangaId) {
        return mangaRepository.getLatestChapter(mangaId);
    }

    // rating mangadex e
    public LiveData<String> getMangaRating(String mangaId) {
        return mangaRepository.getMangaRating(mangaId);
    }
}