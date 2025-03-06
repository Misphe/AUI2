import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CategoriesService } from '../services/categories.service';

@Component({
  selector: 'app-element-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div *ngIf="element">
      <h1>Song Details</h1>
      <p><strong>Artist ID:</strong> {{ categoryId }}</p>
      <p><strong>Song ID:</strong> {{ element.id }}</p>
      <p><strong>Name:</strong> {{ element.name }}</p>
      <p><strong>Length:</strong> {{ element.length }} seconds</p>
    </div>
    <button [routerLink]="['/category-details', categoryId]">Back</button>
  `,
  styles: [],
})
export class ElementDetailsComponent implements OnInit {
  categoryId: string | null = null;
  elementId: string | null = null;
  element: { id: string; name: string; length: number } | null = null;

  constructor(
    private activatedRoute: ActivatedRoute,
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
            console.error('error fetching song data', error);
          }
        );
      }
    });
  }
}
