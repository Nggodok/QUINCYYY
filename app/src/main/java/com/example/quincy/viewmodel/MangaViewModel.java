package com.example.quincy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.quincy.data.model.TagModel;
import com.example.quincy.data.API.ApiClient;
import com.example.quincy.data.API.ApiService;
import com.example.quincy.data.model.MangaModel;
import com.example.quincy.data.repository.MangaRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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

    // Cari manga berdasarkan judul
    public LiveData<List<MangaModel>> searchManga(String title, int limit, int offset) {
        return mangaRepository.searchManga(title,
                new ArrayList<>(selectedGenreIds),
                new ArrayList<>(selectedStatuses),
                limit, offset);
    }

    // ===== Filter terpilih (hidup selama Activity) =====
    private final Set<String> selectedGenreIds = new LinkedHashSet<>();
    private final Set<String> selectedStatuses = new LinkedHashSet<>();
    private MutableLiveData<List<TagModel>> genres;

    public Set<String> getSelectedGenreIds() { return selectedGenreIds; }
    public Set<String> getSelectedStatuses() { return selectedStatuses; }

    public void setSelectedGenreIds(Collection<String> ids) {
        selectedGenreIds.clear();
        selectedGenreIds.addAll(ids);
    }

    public void setSelectedStatuses(Collection<String> statuses) {
        selectedStatuses.clear();
        selectedStatuses.addAll(statuses);
    }

    public boolean hasActiveFilter() {
        return !selectedGenreIds.isEmpty() || !selectedStatuses.isEmpty();
    }

    // Diambil sekali saja, setelah itu dipakai ulang
    public LiveData<List<TagModel>> getGenres() {
        if (genres == null) {
            genres = new MutableLiveData<>();
            mangaRepository.fetchGenres(genres);
        }
        return genres;
    }

    // Buat tombol "coba lagi"
    public void reloadGenres() {
        if (genres == null) genres = new MutableLiveData<>();
        mangaRepository.fetchGenres(genres);
    }
}