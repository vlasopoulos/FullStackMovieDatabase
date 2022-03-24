export interface TitleSearchContent {
    tconst: string;
    titleType: string;
    primaryTitle: string;
    isAdult: boolean;
    startYear: number;
    endYear: number;
    genres: string[];
    averageRating: number;
    userRating: number;
    watched: boolean;
    watchlist: boolean;
    
}

export interface WatchedContent {
    tconst: string;
    primaryTitle: string;
    titleType: string;
    averageRating: number;
    userRating: number;
    watchlist: boolean;
}

export interface WatchlistContent {
    tconst: string;
    primaryTitle: string;
    titleType: string;
    averageRating: number;
    userRating: number;
    watched: boolean;
}

export interface Sort {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
}

export interface Pageable {
    sort: Sort;
    offset: number;
    pageNumber: number;
    pageSize: number;
    unpaged: boolean;
    paged: boolean;
}

export interface Sort2 {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
}

export interface TitleSearchRootObject {
    content: TitleSearchContent[];
    pageable: Pageable;
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    sort: Sort2;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}

export interface WatchedRootObject {
    content: WatchedContent[];
    pageable: Pageable;
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    sort: Sort2;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}

export interface WatchlistRootObject {
    content: WatchlistContent[];
    pageable: Pageable;
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    sort: Sort2;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}

export interface TitleRootObject {
    tconst: string;
    titleType: string;
    primaryTitle: string;
    originalTitle: string;
    isAdult: boolean;
    startYear: number;
    endYear: number;
    runtimeMinutes: number;
    genres: string[];
    directors: string[];
    writers: string[];
    averageRating: number;
    numVotes: number;
    userRating: number;
    watched: boolean;
    watchlist: boolean;
}

export interface NamesFromNconsts {
    nconst: string;
    primary_name: string;
}

