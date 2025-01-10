import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CategoriesService } from '../services/categories.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-element',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div *ngIf="element">
      <h1>Edit Song: {{ element.name }}</h1>
      <form (ngSubmit)="updateElement()">
        <label for="name">Name:</label>
        <input
          id="name"
          type="text"
          [(ngModel)]="element.name"
          name="name"
          required
        />

        <label for="length">Length:</label>
        <input
          id="length"
          type="number"
          [(ngModel)]="element.length"
          name="length"
          required
        />

        <button type="submit" [disabled]="!element.name || !element.length">
          Update Song
        </button>
      </form>
    </div>
  `,
  styles: [
    `
      button {
        background-color: #28a745;
        color: white;
        padding: 10px;
        cursor: pointer;
      }
      button:disabled {
        background-color: #ddd;
      }
    `,
  ],
})
export class EditElementComponent implements OnInit {
  categoryId: string | null = null;
  elementId: string | null = null;
  element: { id: string; name: string; length: number } | null = null;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private categoriesService: CategoriesService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.categoryId = params.get('categoryId');
      this.elementId = params.get('elementId');

      if (this.categoryId && this.elementId) {
        this.categoriesService.getElement(this.elementId).subscribe(
          (element) => {
            this.element = element;
          },
          (error) => {
            console.error('error fetching song', error);
          }
        );
      }
    });
  }

  updateElement(): void {
    if (this.elementId && this.categoryId && this.element) {
      if (this.element.name.trim() && this.element.length !== null) {
        this.categoriesService.updateElement(this.element).subscribe(
          () => {
            alert('song updated');
            this.router.navigate([`/category-details/${this.categoryId}`]);
          },
          (error) => {
            console.error('error updating song', error);
          }
        );
      }
    }
  }
}
