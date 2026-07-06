# Precision Agriculture Analytics: Crop Recommendation & Yield Prediction System

[![Python 3.13+](https://img.shields.io/badge/python-3.13+-blue.svg)](https://www.python.org/)
[![Scikit-Learn](https://img.shields.io/badge/sklearn-latest-orange.svg)](https://scikit-learn.org/)
[![Machine Learning](https://img.shields.io/badge/ML-Classification%20%26%20Regression-green.svg)]()

This repository hosts a comprehensive machine learning framework designed to solve critical agricultural challenges through data-driven decisions. The system operates on a dual architecture: **Crop Recommendation** (predicting the optimal crop category for given environmental conditions) and **Crop Yield Prediction** (forecasting numerical yields in tons per hectare). 

The implementation bridges foundational domain research (e.g., historical wheat yield analyses using MICE imputation) with modern predictive pipeline construction, validation methodologies, and **Explainable AI (XAI)** interpretability.

---

## 📋 Table of Contents
- [Part 1: Research Paper & Project Overview](#part-1--research-paper-overview)
  - [Introduction & Problem Statement](#introduction--problem-statement)
  - [Dataset Description](#dataset-description)
  - [Methodology & Pipeline Architecture](#methodology--architecture)
  - [Explainable AI (XAI) Approach](#explainable-ai-x-ai-methods)
- [Part 2: Code Implementation & Analysis](#part-2--code-implementation--analysis)
  - [Project Architecture Stack](#code-architecture-overview)
  - [Data Preprocessing & Feature Engineering](#data-preprocessing--feature-engineering)
  - [Classification Models & Evaluation](#classification-models-comparison)
  - [Regression Models & Evaluation](#regression-models-comparison)
  - [Feature Importance Diagnostics](#feature-importance-diagnostics)
- [How to Run](#how-to-run)

---

## PART 1: RESEARCH PAPER OVERVIEW

### Introduction & Problem Statement
Modern agriculture faces deep systemic risks including unpredictable weather patterns, soil degradation, nutrient depletion, and water scarcity. Traditional farming methodologies rely on heuristic or historical patterns, leading to sub-optimal crop selection and poor resource utilization. 

**Research Objectives:**
*   **Crop Recommendation:** Classify fields into 22 distinct crop categories optimized for N, P, K soil profiles and local climate factors.
*   **Yield Prediction:** Formulate a robust regression framework for long-term historical prediction (modeled on Pakistan wheat data historicals from 1992–2024).
*   **Model Comparison:** Benchmark 7+ machine learning algorithms across classification and regression spaces.
*   **Explainable AI (XAI):** Deploy LIME (Local Interpretable Model-agnostic Explanations) to foster user adoption and model trust.

### Dataset Description
The system evaluates two primary underlying data streams:
1.  **Crop Recommendation Dataset:** A balanced Kaggle dataset consisting of 2,200 records across 22 crop classes, tracking Nitrogen (N), Phosphorus (P), Potassium (K), Soil pH, temperature, humidity, and rainfall.
2.  **Wheat Yield Dataset:** Derived from FAO/World Bank records tracking historical area (hectares), average temperature, rainfall, and pesticide consumption. Missing blocks (2014-2024) were resolved using **MICE (Multivariate Imputation by Chained Equations)**.

---

## PART 2: CODE IMPLEMENTATION & ANALYSIS

The practical codebase references the provided file verbatim: `crop-yield.csv`. It executes a unified data pipeline from Exploratory Data Analysis (EDA) to production-ready interactive predictors.

### Data Preprocessing & Feature Engineering
*   **Outlier Mitigation:** Evaluates continuous numerical columns via the **Interquartile Range (IQR) Method** to filter skewed signals.
*   **Categorical Encoding:** Employs `LabelEncoder` to cleanly map multi-class targets and environment tags (`Soil_Type`, `Region`, `Season`, `Irrigation_Type`).
*   **Feature Scaling:** Deploys a zero-mean, unit-variance conversion via `StandardScaler` to ensure distance-based models (SVM, KNN) are not biased by disparate feature magnitudes.
*   **Validation Strategy:** Implements an 80-20 stratified split for classification to guarantee identical category densities across training and test spaces.

---

### Classification Models Comparison
The classification workspace evaluates 5 distinct paradigms to isolate the best crop category recommendation:

| Model | Accuracy | Recall | Precision | F1-Score |
| :--- | :---: | :---: | :---: | :---: |
| **Random Forest Classifier** | **0.6130** | **0.6130** | **0.6001** | **0.6038** |
| Gaussian Naive Bayes | 0.5940 | 0.5940 | 0.5828 | 0.5848 |
| Decision Tree Classifier | 0.5895 | 0.5895 | 0.5866 | 0.5877 |
| Support Vector Machine (SVC) | 0.5365 | 0.5365 | 0.5377 | 0.5366 |
| K-Nearest Neighbors (KNN) | 0.3520 | 0.3520 | 0.3598 | 0.3513 |

> **Key Takeaway:** Ensemble models drastically outperformed basic linear and distance metrics. Per-class diagnostics reveal flawless prediction metrics (1.00 Precision/Recall) for structural distinct crops like Potato and Sugarcane, while mixed continuous features (Rice/Wheat) experience boundary overlaps.

---

### Regression Models Comparison
The regression engine predicts continuous outcomes (`Crop_Yield_ton_per_hectare`) using standard and regularized variants:

| Model | $R^2$ Score | MAE | RMSE |
| :--- | :---: | :---: | :---: |
| **Gradient Boosting Regressor** | **0.997939** | **0.874820** | **1.095923** |
| XGBoost Regressor | 0.997833 | 0.896710 | 1.123983 |
| Random Forest Regressor | 0.997793 | 0.910112 | 1.134102 |
| Linear Regression | 0.133909 | 16.565416 | 22.468317 |
| Support Vector Regressor (SVR)| -0.133313 | 13.467348 | 25.701802 |

> **Key Takeaway:** High non-linearity within data clusters renders linear baselines and default radial kernels (SVR) fully ineffective. Tree-based sequential boosting mechanisms (**GradientBoost** / **XGBoost**) successfully converge to map exact agricultural configurations, capturing $99.79\%$ of structural variance.

---

### Model Performance & Diagnostics

#### 1. Classification Feature Urgency
Tree-based impurity reductions identify `Crop_Yield_ton_per_hectare` as the single highest indicator variable when identifying a structural crop configuration, with secondary support weights derived from `Fertilizer_Used` and environmental variables (`Rainfall`, `Soil_Moisture`).

#### 2. Regression Feature Urgency
Conversely, when evaluating targeted yield quantities, the engineered `Crop_Type` string features dominate the split hierarchy. Environmental modifiers drop down the importance spectrum, confirming that base crop classification dictates baseline limits, while inputs like fertilizer tweak final yield placement within those distinct limits.

---

## 🚀 How to Run

### Prerequisites
Ensure your local development space contains the core runtime libraries:
```bash
pip install numpy pandas scikit-learn matplotlib seaborn xgboost
