import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriesService } from '../services/categories.service';
import { Song } from '../models/song.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div *ngIf="category">
      <h1>{{ category.name }}</h1>
      <p>Followers: {{ category.followers }}</p>
      <p>Id: {{ category.id }}</p>

      <h3>Songs</h3>
      <ul>
        <li *ngFor="let song of songs">
          {{ song.name }}
          <button (click)="deleteSong(song.id)">Delete</button>
          <button [routerLink]="['/edit-element', category.id, song.id]">
            Edit
          </button>
          <button [routerLink]="['/element-details', category.id, song.id]">
            Details
          </button>
        </li>
      </ul>

      <button [routerLink]="['/add-element', category.id]">Add Song</button>
      <button [routerLink]="['/']">Back</button>
    </div>
  `,
  styles: [
    `
      h1 {
        font-size: 2em;
      }

      button {
        background-color: #d9534f;
        color: white;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
      }

      button:hover {
        background-color: #c9302c;
      }
    `,
  ],
})
export class CategoryDetailsComponent {
  category: any = null;
  songs: Song[] = [];
  categoryId: string | null = null;

  constructor(
    private activatedRoute: ActivatedRoute,
    private categoriesService: CategoriesService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.categoryId = params.get('id');
      if (this.categoryId) {
        this.fetchCategoryDetails(this.categoryId);
      }
    });
  }

  fetchCategoryDetails(id: string): void {
    this.categoriesService.getCategoryById(id).subscribe(
      (categoryData) => {
        this.category = categoryData;
        this.fetchSongsByCategoryId(id);
      },
      (error) => {
        console.error('Error fetching category details', error);
      }
    );
  }

  fetchSongsByCategoryId(categoryId: string): void {
    this.categoriesService.getElementsByCategoryId(categoryId).subscribe(
      (songsData) => {
        this.songs = songsData;
      },
      (error) => {
        console.error('Error fetching songs', error);
      }
    );
  }

  deleteSong(songId: string): void {
    if (this.category) {
      this.categoriesService.deleteElement(songId).subscribe(
        () => {
          this.songs = this.songs.filter((song) => song.id !== songId);
        },
        (error) => {
          console.error('Error deleting song', error);
        }
      );
    }
  }
}
