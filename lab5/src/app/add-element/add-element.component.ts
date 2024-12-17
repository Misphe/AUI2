import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriesService } from '../services/categories.service';
import { CommonModule } from '@angular/common';
import { addSongDto } from '../models/addSongDto.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-element',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div *ngIf="categoryId">
      <h2>Add New Song</h2>
      <form (ngSubmit)="addElement()">
        <div>
          <label for="name">Song Name</label>
          <input
            type="text"
            id="name"
            [(ngModel)]="newSong.name"
            name="name"
            required
          />
        </div>
        <div>
          <label for="followers">Song Length</label>
          <input
            type="number"
            id="followers"
            [(ngModel)]="newSong.length"
            name="followers"
            required
          />
        </div>
        <button type="submit" [disabled]="isLoading">Add Song</button>
      </form>
    </div>
  `,
  styles: [
    `
      form {
        display: flex;
        flex-direction: column;
        max-width: 400px;
        margin: 0 auto;
      }

      form div {
        margin-bottom: 10px;
      }

      button {
        background-color: #28a745;
        color: white;
        border: none;
        padding: 10px;
        cursor: pointer;
      }

      button:disabled {
        background-color: #ccc;
      }
    `,
  ],
})
export class AddElementComponent implements OnInit {
  categoryId: string | null = null;
  newSong: addSongDto = { name: '', length: 0 };
  isLoading = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private categoriesService: CategoriesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.categoryId = params.get('id');
    });
  }

  addElement(): void {
    if (this.categoryId) {
      this.isLoading = true;
      this.categoriesService
        .addElement(this.categoryId, this.newSong)
        .subscribe(
          () => {
            alert('Song added successfully!');
            this.router.navigate([`/category-details/${this.categoryId}`]);
          },
          (error) => {
            console.error('Error adding song', error);
          }
        );
    }
  }
}
