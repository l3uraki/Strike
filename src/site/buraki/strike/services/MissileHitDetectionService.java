package site.buraki.strike.services;

import site.buraki.strike.models.geometry.Point;

/**
 * Служба определения попадания ракеты по цели.
 *
 * @author Расим "Buraki" Эминов
 * @since 0.2.0
 */
public class MissileHitDetectionService {

    /**
     * Определить попадание ракеты по цели.
     *
     * <p>Цель помещена в декартову прямоугольную систему координат на
     * плоскости:
     *
     * <ul>
     *     <li>В первом квадранте цель представлена прямоугольником с
     *     шириной, равной радиусу цели и расположенной на оси абсцисс,
     *     и высотой, равной половине радиуса цели и расположенной на
     *     оси ординат.
     *
     *     <li>Во втором квадранте цель представлена сектором с
     *     радиусом, равным радиусу цели.
     *
     *     <li>В третьем квадранте цель представлена прямоугольным
     *     треугольником с катетами, равными радиусу цели и половине
     *     радиуса цели и расположенными на оси абсцисс и оси ординат
     *     соответственно.
     *
     *     <li>В четвёртом квадранте цель ничем не представлена.
     * </ul>
     *
     * @param strikePoint Точка ракетного удара.
     * @param targetRadius Радиус цели.
     * @return {@code true}, если цель поражена, иначе {@code false}.
     * @author Расим "Buraki" Эминов
     * @see Point
     * @since 0.2.0
     */
    public static boolean detectMissileHit(Point strikePoint, float targetRadius) {
        int quadrant = strikePoint.getQuadrant();

        if (quadrant == 1) {
            return strikePoint.getX() <= targetRadius
                && strikePoint.getY() <= targetRadius/2;
        }

        if (quadrant == 2) {
            return strikePoint.getDistance() <= targetRadius;
        }

        if (quadrant == 3) {
            return strikePoint.getY() >= strikePoint.getX()/-2 - targetRadius/2;
        }

        if (quadrant == 4) {
            return false;
        }

        return -targetRadius <= strikePoint.getX() && strikePoint.getX() <= targetRadius
            && -targetRadius/2 <= strikePoint.getY() && strikePoint.getY() <= targetRadius;
    }
}
