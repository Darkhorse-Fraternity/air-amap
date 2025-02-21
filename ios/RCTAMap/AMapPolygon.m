//
// Created by Leland Richardson on 12/27/15.
// Copyright (c) 2015 Facebook. All rights reserved.
//

#import "AMapPolygon.h"
#import "UIView+React.h"


@implementation AMapPolygon {

}

- (void)setFillColor:(UIColor *)fillColor {
    _fillColor = fillColor;
    [self update];
}

- (void)setStrokeColor:(UIColor *)strokeColor {
    _strokeColor = strokeColor;
    [self update];
}

- (void)setStrokeWidth:(CGFloat)strokeWidth {
    _strokeWidth = strokeWidth;
    [self update];
}

- (void)setLineJoin:(CGLineJoin)lineJoin {
    _lineJoin = lineJoin;
    [self update];
}

- (void)setLineCap:(CGLineCap)lineCap {
    _lineCap = lineCap;
    [self update];
}

- (void)setMiterLimit:(CGFloat)miterLimit {
    _miterLimit = miterLimit;
    [self update];
}

- (void)setLineDashPhase:(CGFloat)lineDashPhase {
    _lineDashPhase = lineDashPhase;
    [self update];
}

- (void)setLineDashPattern:(NSArray <NSNumber *> *)lineDashPattern {
    _lineDashPattern = lineDashPattern;
    [self update];
}

- (void)setCoordinates:(NSArray<AMapCoordinate *> *)coordinates {
    _coordinates = coordinates;
    CLLocationCoordinate2D coords[coordinates.count];
    for(int i = 0; i < coordinates.count; i++)
    {
        coords[i] = coordinates[i].coordinate;
    }
    self.polygon = [MAPolygon polygonWithCoordinates:coords count:coordinates.count];
    // TODO: we could lazy-initialize the polygon, since we don't need it until the
    // polygon is in view.
    self.renderer = [[MAPolygonRenderer alloc] initWithPolygon:self.polygon];
    [self update];
}

- (void) update
{
    if (!_renderer) return;
    _renderer.fillColor = _fillColor;
    _renderer.strokeColor = _strokeColor;
    _renderer.lineWidth = _strokeWidth;
//    _renderer.lineCap = _lineCap;
//    _renderer.lineJoin = _lineJoin;
    _renderer.miterLimit = _miterLimit;
//    _renderer.lineDashPhase = _lineDashPhase;
//    _renderer.lineDashPattern = _lineDashPattern;

    if (_map == nil) return;
    [_map.mapView removeOverlay:self];
    [_map.mapView addOverlay:self];
}

#pragma mark MAOverlay implementation

- (CLLocationCoordinate2D) coordinate
{
    return self.polygon.coordinate;
}

- (MAMapRect) boundingMapRect
{
    return self.polygon.boundingMapRect;
}

- (BOOL)intersectsMapRect:(MAMapRect)mapRect
{
//    BOOL answer = [self.polygon intersectsMapRect:mapRect];
//    return answer;
    return NO;
}

- (BOOL)canReplaceMapContent
{
    return NO;
}





















































@end